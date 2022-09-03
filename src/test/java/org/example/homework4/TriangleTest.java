package org.example.homework4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class TriangleTest {
    private static final Logger logger = LoggerFactory.getLogger("TriangleTest");
    final double accuracy = 0.001;
    TriangleArea triangleArea;

    @BeforeEach
    void getInstance(){
        triangleArea = new TriangleArea();
    }

    @ParameterizedTest
    @DisplayName("Given: sides less or equal to zero. When get triangle area, then exception")
    @MethodSource("triangleWrongSides")
    public void testTriangleArea01(int a, int b, int c) {
        logger.warn("Exception in TriangleArea");
        Assertions.assertThrows(InvalidTriangleSidesException.class, ()-> triangleArea.getTriangleArea(a, b, c));
    }

    @ParameterizedTest
    @DisplayName("Given: sides greater than zero. When get triangle area, then right result of Geron's formula")
    @CsvSource({"1, 1, 1, 0.433", "2, 2, 2, 1.732", "1, 2, 3, 0" })
    public void testTriangleArea02(int a, int b, int c, double expectedResult) throws InvalidTriangleSidesException {
        double actualResult = triangleArea.getTriangleArea(a, b, c);
        logger.info("Triangle's area: " + actualResult);
        Assertions.assertTrue(Math.abs(expectedResult - actualResult) < accuracy);
    }

    private static Stream<Arguments> triangleWrongSides(){
        return Stream.of(Arguments.of(0,0,0),
                Arguments.of(0,-1,-1),
                Arguments.of(-1,0,-1),
                Arguments.of(-1,-1,0),
                Arguments.of(-1,0,0),
                Arguments.of(0,-1,0),
                Arguments.of(0,0,-1),
                Arguments.of(-1,-1,-1),
                Arguments.of(1,1,0),
                Arguments.of(0,1,1),
                Arguments.of(1,0,1),
                Arguments.of(1,1,-1),
                Arguments.of(-1,1,1),
                Arguments.of(1,1,-1));
    }
}
