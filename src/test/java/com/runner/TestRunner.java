package com.runner;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.jbehave.annotations.Metafilter;
import org.jbehave.core.annotations.BeforeStory;

@Metafilter("+Testing")
public class TestRunner extends SerenityStories {
    @BeforeStory
    public void clearSerenitySession(){
       Serenity.getCurrentSession().clear();
    }
}
