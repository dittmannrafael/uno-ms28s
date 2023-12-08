package Interfaces;

import javax.swing.*;
import java.awt.*;

public class ColorSelectionWindow {
    public String selectedPalette;

    public ColorSelectionWindow() {
        Object[] options = {"Padrão", "Acromatopsia", "Tritanomalia", "Deuteromalia", "Protanomalia"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma paleta de cores:", "Seleção de Cores", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        // Defina a paleta com base na escolha do jogador
        UNOConstants unoConstants = new UNOConstants();
        // Defina a paleta com base na escolha do jogador
        switch (choice) {
            case 0: // Padrão
                selectedPalette = "Padrao";
                break;
            case 1: // Acromatopsia
                selectedPalette = "Acromatopsia";
                break;
            case 2: // Tritanomalia
                selectedPalette = "Tritanomalia";
                break;
            case 3: // Deuteromalia
                selectedPalette = "Deuteromalia";
                break;
            case 4: // Protanomalia
                selectedPalette = "Protanomalia";
                break;
            default: // Padrão (caso o jogador cancele a seleção)
                selectedPalette = "Padrão";
                break;
        }
    }

    public String getSelectedPalette() {
        return selectedPalette;
    }
}