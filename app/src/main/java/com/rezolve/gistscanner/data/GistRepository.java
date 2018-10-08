package com.rezolve.gistscanner.data;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GistRepository {
    private final IGistDataSource gistApi;

    @Inject
    public GistRepository(@Remote IGistDataSource gistApi) {
        this.gistApi = gistApi;
    }
}
