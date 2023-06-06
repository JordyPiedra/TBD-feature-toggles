package es.urjc.code.daw.library;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

public enum Features implements Feature {
    @Label("line breaker")
    FEATURE_LINE_BREAKER,

    @Label("async notification")
    FEATURE_ASYNC_NOTIFICATION
}