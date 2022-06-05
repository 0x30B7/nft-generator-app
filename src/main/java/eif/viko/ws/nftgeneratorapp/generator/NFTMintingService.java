package eif.viko.ws.nftgeneratorapp.generator;

import eif.viko.ws.nftgeneratorapp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NFTMintingService {

    private static final AtomicInteger TASK_ID_GENERATOR = new AtomicInteger(1);

    @Qualifier("DiskImageService")
    private final ImageService imageService;
    private ExecutorService executorService;

    @Autowired
    public NFTMintingService(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostConstruct
    private void onPostInit() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
    }

    public void submit(Artifact artifact) {
        executorService.submit(new NFTMintingTask(TASK_ID_GENERATOR.getAndIncrement(), artifact, imageService,
                new LinkedList<>(), Collections.emptyList(), NFTMintingCallback.empty()));
    }

}
