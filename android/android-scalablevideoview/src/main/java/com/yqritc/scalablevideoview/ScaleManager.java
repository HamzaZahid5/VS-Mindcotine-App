package com.yqritc.scalablevideoview;

import android.graphics.Matrix;

public class ScaleManager {
    private final Size viewSize;
    private final Size videoSize;

    public ScaleManager(Size viewSize, Size videoSize) {
        this.viewSize = viewSize;
        this.videoSize = videoSize;
    }

    public Matrix getScaleMatrix(ScalableType scalableType) {
        // Minimal implementation: return identity matrix to satisfy API.
        return new Matrix();
    }
}


