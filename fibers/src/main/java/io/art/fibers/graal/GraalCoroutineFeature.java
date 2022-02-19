package io.art.fibers.graal;

import com.oracle.svm.core.annotate.*;
import org.graalvm.nativeimage.hosted.*;
import static io.art.core.graal.GraalNativeRegistrator.*;
import static io.art.fibers.constants.FiberConstants.GraalConstants.*;

@AutomaticFeature
public class GraalCoroutineFeature implements Feature {
    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        registerStaticNonJniLibrary(access, coroutineLibraryFileName());
    }
}
