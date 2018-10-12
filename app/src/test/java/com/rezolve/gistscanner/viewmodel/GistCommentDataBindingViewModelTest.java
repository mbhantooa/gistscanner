package com.rezolve.gistscanner.viewmodel;

import com.rezolve.gistscanner.model.GistComment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class GistCommentDataBindingViewModelTest {

    @Mock
    GistComment gistComment;

    @InjectMocks
    GistCommentDataBindingViewModel gistCommentDataBindingViewModel;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(gistComment.getUpdatedAt())
                .thenReturn("2018-10-07T12:31:00Z");
    }

    @Test
    public void getLastUpdatedAt() {
        String expectedFormattedDateString = "07 Oct 2018 at 12:31";
        assertEquals(expectedFormattedDateString,
                gistCommentDataBindingViewModel.getLastUpdatedAt());

    }
}