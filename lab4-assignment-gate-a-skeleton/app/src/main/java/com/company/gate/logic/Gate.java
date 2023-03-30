package com.company.gate.logic;

/**
 * This file defines the Gate class.
 */
public class Gate {

    public static final int IN = 1;
    public static final int OUT = -1;
    public static final int CLOSED = 0;

    private int swingDirection = CLOSED;
    //изменение напрвлен
        public boolean setSwing(int direction) {
        if (direction == IN || direction == OUT || direction == CLOSED) {
            swingDirection = direction;
            return true;
        }
        return false;
        }
        //открываем ворота в определенном направления ворот

        public boolean open(int direction) {
            if (swingDirection == CLOSED && (direction == IN || direction == OUT)) {
                swingDirection = direction;
                return true;
            } else {
                return false;
            }
        }
        // закрываем ворота
        public void close() {
            swingDirection = CLOSED;
        }

        //получение текущео напрвления открытия ворот
        public int getSwingDirection() {
            return swingDirection;
        }
        // метод для получения информации от ворот
        public String toString() {
            if (swingDirection == IN) {
                return "This gate is open and swings to enter the pen only";
            } else if (swingDirection == OUT) {
                return "This gate is open and swings to exit the pen only";
            } else {
                return "This gate is closed";
            }
        }
//считаем тех кто прошел через ворота
        public int thru(int count) {
            if (swingDirection == OUT) {
                return -count;
            } else if (swingDirection == IN) {
                return count;
            } else {
                return 0;
            }
        }
    }
