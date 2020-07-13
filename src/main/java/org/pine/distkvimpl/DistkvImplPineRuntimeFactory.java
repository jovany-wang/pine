package org.pine.distkvimpl;

import org.pine.api.PineRuntime;
import org.pine.api.PineRuntimeFactory;

public class DistkvImplPineRuntimeFactory implements PineRuntimeFactory {

    public PineRuntime createPineRuntime() {
        return new PineRuntimeDistkvImpl();
    }
}
