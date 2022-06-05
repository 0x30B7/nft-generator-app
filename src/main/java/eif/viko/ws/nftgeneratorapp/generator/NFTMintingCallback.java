package eif.viko.ws.nftgeneratorapp.generator;

/**
 * Interface representing an NFT minting task progress callback
 */
public interface NFTMintingCallback {

    static NFTMintingCallback empty() {
        return new NFTMintingCallback() {
            public void onComplete(Artifact artifact) { }

            public void onError(Exception ex) { }
        };
    }

    /**
     * Invoked when the minting task completes successfully
     *
     * @param artifact The minted artifact, containing an associated image id
     */
    void onComplete(Artifact artifact);

    /**
     * Invoked if the minting process encounters an error and is cancelled
     *
     * @param ex The cause of the error
     */
    void onError(Exception ex);

}
