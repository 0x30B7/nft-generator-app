package eif.viko.ws.nftgeneratorapp;

import eif.viko.ws.nftgeneratorapp.generator.NFTMintingService;
import eif.viko.ws.nftgeneratorapp.generator.pipeline.NFTMintingComponentFactory;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Class representing the entry point of the application
 */
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

    /**
     * Constructs a shared {@link NFTMintingComponentFactory} instance, used for dependency injection
     *
     * @return The constructed instance
     */
    @Bean
    public NFTMintingComponentFactory getNFTMintingComponentFactory() {
        return new NFTMintingComponentFactory();
    }

    /**
     * Constructs a shared {@link RestTemplate} instance, used for dependency injection
     *
     * @return The constructed instance
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
