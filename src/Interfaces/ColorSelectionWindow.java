package Interfaces;

import javax.swing.*;
import java.awt.*;

public class ColorSelectionWindow {
    public String selectedPalette;

    public ColorSelectionWindow() {
        Object[] options = {"Padrão", "Acromatopsia", "Tritanomalia", "Deuteromalia", "Protanomalia"};
        int choice = JOptionPane.showOptionDialog(null, "Escolha uma paleta de cores:", "Seleção de Cores", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        //String selectedPalette;
        // Defina a paleta com base na escolha do jogador
        UNOConstants unoConstants = new UNOConstants();
        // Defina a paleta com base na escolha do jogador
        switch (choice) {
            case 0: // Padrão
                selectedPalette = "Padrao";
                unoConstants.setColors(selectedPalette); // Define as cores para a paleta selecionada
                break;
            case 1: // Acromatopsia
                selectedPalette = "Acromatopsia";
                // Defina as cores para Acromatopsia aqui
                break;
            case 2: // Tritanomalia
                selectedPalette = "Tritanomalia";
                // Defina as cores para Tritanomalia aqui
                unoConstants.setColors(selectedPalette);
                break;
            case 3: // Deuteromalia
                selectedPalette = "Deuteromalia";
                // Defina as cores para Deuteromalia aqui
                break;
            case 4: // Protanomalia
                selectedPalette = "Protanomalia";
                // Defina as cores para Protanomalia aqui
                break;
            default: // Padrão (caso o jogador cancele a seleção)
                selectedPalette = "Padrão";
                // Defina as cores para o padrão aqui, se necessário
                break;
        }
    }

    public String getSelectedPalette() {
        return selectedPalette;
    }
}