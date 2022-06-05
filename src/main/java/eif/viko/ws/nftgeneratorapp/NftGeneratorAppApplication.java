package eif.viko.ws.nftgeneratorapp;

import eif.viko.ws.nftgeneratorapp.generator.NFTMintingService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class NftGeneratorAppApplication {

    private final NFTMintingService nftMintingService;

    @Autowired
    public NftGeneratorAppApplication(NFTMintingService nftMintingService) {
        this.nftMintingService = nftMintingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(NftGeneratorAppApplication.class, args);
    }

}
