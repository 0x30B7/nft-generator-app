package eif.viko.ws.nftgeneratorapp.generator;

public interface NFTMintingCallback {

    static NFTMintingCallback empty() {
        return new NFTMintingCallback() {
            public void onComplete(Artifact artifact) { }
            public void onError(Exception ex) { }
        };
    }

    void onComplete(Artifact artifact);

    void onError(Exception ex);

}
