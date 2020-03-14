package com.tayyabsa.association;

public class Composer {

    // by declaring final we achieve composition
    public final Composed composed;

    public Composer(Composed composed) {
        this.composed = composed;
    }
}
