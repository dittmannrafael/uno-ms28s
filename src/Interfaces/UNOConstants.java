package Interfaces;

import java.awt.Color;
/*
Code created by Josh Braza
*/
public class UNOConstants {
    //Colors
    public Color RED /*= new Color(192, 80, 77)*/;
    public Color BLUE /*= new Color(31, 73, 125)*/;
    public Color GREEN /*= new Color(0, 153, 0)*/;
    public Color YELLOW /*= new Color(255, 204, 0)*/;
    public UNOConstants() {

    }

    // Getters para as cores
    public Color getRED(String palette) {
        switch (palette) {
            case "Padrao":
                RED = new Color(192, 80, 77);
                break;
            case "Acromatopsia":
                // Defina as cores para Acromatopsia aqui
                RED = new Color(148, 148, 148);
                break;
            case "Tritanomalia":
                // Defina as cores para Tritanomalia aqui
                RED = new Color(255, 25, 94);
                break;
            case "Deuteromalia":
                // Defina as cores para Deuteromalia aqui
                RED = new Color(165, 90, 47);
                break;
            case "Protanomalia":
                // Defina as cores para Protanomalia aqui
                RED = new Color(159, 87, 64);
                break;
            default:
                // Defina um valor padrão aqui, se necessário
                RED = new Color(255, 25, 94);
                break;
        }
        return RED;
    }

    public Color getBLUE(String palette) {
        switch (palette) {
            case "Padrao":
                BLUE = new Color(31, 73, 125);
                break;
            case "Acromatopsia":
                // Defina as cores para Acromatopsia aqui
                BLUE = new Color(105, 105, 103);
                break;
            case "Tritanomalia":
                // Defina as cores para Tritanomalia aqui
                BLUE = new Color(0, 101, 146);
                break;
            case "Deuteromalia":
                // Defina as cores para Deuteromalia aqui
                BLUE = new Color(28, 95, 184);
                break;
            case "Protanomalia":
                // Defina as cores para Protanomalia aqui
                BLUE = new Color(26, 110, 191);
                break;
            default:
                // Defina um valor padrão aqui, se necessário
                BLUE = new Color(31, 73, 125);
                break;
        }
        return BLUE;
    }

    public Color getGREEN(String palette) {
        switch (palette) {
            case "Padrao":
                GREEN = new Color(0, 153, 0);
                break;
            case "Acromatopsia":
                // Defina as cores para Acromatopsia aqui
                GREEN = new Color(180, 180, 180);
                break;
            case "Tritanomalia":
                // Defina as cores para Tritanomalia aqui
                GREEN = new Color(44, 204, 235);
                break;
            case "Deuteromalia":
                // Defina as cores para Deuteromalia aqui
                GREEN = new Color(217, 227, 123);
                break;
            case "Protanomalia":
                // Defina as cores para Protanomalia aqui
                GREEN = new Color(151, 211, 68);
                break;
            default:
                // Defina um valor padrão aqui, se necessário
                GREEN = new Color(0, 153, 0);
                break;
        }
        return GREEN;
    }

    public Color getYELLOW(String palette) {
        switch (palette) {
            case "Padrao":
                YELLOW = new Color(255, 204, 0);
                break;
            case "Acromatopsia":
                // Defina as cores para Acromatopsia aqui
                YELLOW = new Color(210, 189, 189);
                break;
            case "Tritanomalia":
                // Defina as cores para Tritanomalia aqui
                YELLOW = new Color(124, 110, 115);
                break;
            case "Deuteromalia":
                // Defina as cores para Deuteromalia aqui
                YELLOW = new Color(242, 222, 10);
                break;
            case "Protanomalia":
                // Defina as cores para Protanomalia aqui
                YELLOW = new Color(245, 216, 12);
                break;
            default:
                // Defina um valor padrão aqui, se necessário
                YELLOW = new Color(255, 204, 0);
                break;
        }
        return YELLOW;
    }
}