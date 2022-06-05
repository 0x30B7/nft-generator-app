package eif.viko.ws.nftgeneratorapp.controller;

import eif.viko.ws.nftgeneratorapp.controller.model.NFTLayer;
import eif.viko.ws.nftgeneratorapp.controller.model.NFTProcessorStep;
import eif.viko.ws.nftgeneratorapp.controller.model.NFTRequest;
import eif.viko.ws.nftgeneratorapp.generator.Artifact;
import eif.viko.ws.nftgeneratorapp.generator.ArtifactLayer;
import eif.viko.ws.nftgeneratorapp.generator.NFTMintingCallback;
import eif.viko.ws.nftgeneratorapp.generator.NFTMintingService;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.PipelineFactory;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.resource.ProcessorStepResourceContext;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ImageProcessorStep;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.step.ProcessorStepService;
import eif.viko.ws.nftgeneratorapp.service.ImageService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/nft")
public class NFTMintingController {

    @Autowired
    private NFTMintingService mintingService;
    @Autowired
    @Qualifier("DiskImageService")
    private ImageService imageService;
    @Autowired
    private PipelineFactory pipelineFactory;

    @PostMapping("/mint")
    public ResponseEntity<?> mintNFT(@RequestBody NFTRequest body) {
        List<ArtifactLayer> layers = new ArrayList<>();
        Queue<List<ImageProcessorStep>> layerProcessorStepsQueue = new LinkedList<>();
        ProcessorStepResourceContext resourceContext;

        try {
            resourceContext = pipelineFactory.createResourceContext(body.getResources());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        for (NFTLayer layer : body.getLayers()) {
            List<ImageProcessorStep> layerProcessorSteps = new ArrayList<>();

            for (NFTProcessorStep processorStep : layer.getProcessorSteps()) {
                try {
                    layerProcessorSteps.add(pipelineFactory.createProcessorStep(resourceContext, processorStep));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().build();
                }
            }

            layerProcessorStepsQueue.add(layerProcessorSteps);
            layers.add(new ArtifactLayer(layer.getImageId(), body.getWidth(), body.getHeight(), 0, 0, 0));
        }

        Artifact artifact = new Artifact(UUID.randomUUID().toString(), body.getWidth(), body.getHeight(), layers);

        int imageId;

        try {
            imageId = mintingService.submit(artifact, layerProcessorStepsQueue, Collections.emptyList(), new NFTMintingCallback() {
                @Override
                public void onComplete(Artifact artifact) {
                    System.out.println("NFT complete!");
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("NFT error: " + ex.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(Map.of("id", imageId));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> fetchNFT(@PathVariable int id) {
        InputStream is;
        int payloadSize;

        try {
            Optional<BufferedImage> optImage = imageService.getNFTImageById(id);

            if (optImage.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(optImage.get(), "png", os);
            byte[] data = os.toByteArray();
            payloadSize = data.length;
            is = new ByteArrayInputStream(data);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", id + ".png"));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(payloadSize)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(is));
    }

}
