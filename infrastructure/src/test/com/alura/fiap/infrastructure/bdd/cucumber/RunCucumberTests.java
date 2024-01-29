package com.alura.fiap.infrastructure.bdd.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "../infrastructure/src/test/resources/features", glue = "com.alura.fiap.infrastructure.bdd.cucumber.steps", publish = true)
public class RunCucumberTests {
}
