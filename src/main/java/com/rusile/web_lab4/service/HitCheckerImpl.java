package com.rusile.web_lab4.service;

import com.rusile.web_lab4.dto.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class HitCheckerImpl implements HitChecker {

    @Override
    public boolean checkHit(Coordinates coordinates) {
        return checkCircle(coordinates) ||
                checkSquare(coordinates) ||
                checkTriangle(coordinates);
    }

    private boolean checkTriangle(Coordinates coordinates) {
        return coordinates.getX() >= 0 &&
                coordinates.getY() >=0 &&
                coordinates.getY() <= -0.5 * coordinates.getX() + coordinates.getR() / 2;
    }

    private boolean checkSquare(Coordinates coordinates) {
        return coordinates.getX() <= 0 &&
                coordinates.getY() >=0 &&
                coordinates.getX() >= -coordinates.getR() &&
                coordinates.getY() <= coordinates.getR() / 2;
    }

    private boolean checkCircle(Coordinates coordinates) {
        return coordinates.getX() >= 0 &&
                coordinates.getY() <=0 &&
                Math.pow(coordinates.getX(), 2) + Math.pow(coordinates.getY(), 2) <= Math.pow(coordinates.getR() / 2, 2);
    }
}
