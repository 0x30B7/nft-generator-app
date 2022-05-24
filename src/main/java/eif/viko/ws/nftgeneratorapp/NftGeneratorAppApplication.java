package eif.viko.ws.nftgeneratorapp;

import eif.viko.ws.nftgeneratorapp.generator.Artifact;
import eif.viko.ws.nftgeneratorapp.generator.ArtifactLayer;
import eif.viko.ws.nftgeneratorapp.generator.NFTMintingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@OpenAPIDefinition
public class NftGeneratorAppApplication implements CommandLineRunner {

    private final NFTMintingService nftMintingService;

    @Autowired
    public NftGeneratorAppApplication(NFTMintingService nftMintingService) {
        this.nftMintingService = nftMintingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(NftGeneratorAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Artifact artifact = new Artifact("demo", 400, 400, Arrays.asList(
//                new ArtifactLayer(1, 400, 400, 0, 0, 1),
//                new ArtifactLayer(7, 400, 400, 0, 0, 2),
//                new ArtifactLayer(5, 400, 400, 0, 0, 3),
//                new ArtifactLayer(2, 400, 400, 0, 0, 4)
//        ));
//
//        nftMintingService.submit(artifact);
    }

}
