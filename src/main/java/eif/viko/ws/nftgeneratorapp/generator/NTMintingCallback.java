package eif.viko.ws.nftgeneratorapp.generator;

public interface NTMintingCallback {

    static NTMintingCallback empty() {
        return new NTMintingCallback() {
            public void onComplete(Artifact artifact) { }
            public void onError(Exception ex) { }
        };
    }

    void onComplete(Artifact artifact);

    void onError(Exception ex);

}
