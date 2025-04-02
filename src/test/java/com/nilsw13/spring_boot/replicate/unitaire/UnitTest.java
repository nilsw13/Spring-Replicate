package com.nilsw13.spring_boot.replicate.unitaire;

/**
 * Marker interface for unit tests.
 *
 * This interface doesn't contain any methods or constants. Its sole purpose
 * is to categorize test classes to enable selective test execution.
 *
 * Tests annotated with @Category(UnitTest.class) can be run separately from
 * integration or component tests when configured in the build tool.
 */
public interface UnitTest {
    // This is an empty marker interface
    // No methods or constants are needed
}