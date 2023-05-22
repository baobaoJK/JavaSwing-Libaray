package com.ksamar.library.tools.border;

import com.ksamar.library.tools.color.Colour;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * 边框
 * @author KSaMar
 * @version 1.0
 */
public class Borders {

    /**
     * 边框
     */
    public static Border textFiledBorder;
    public static Border topPanelBorder;
    public static Border searchFiledBorder;
    public static Border viewBorder;
    public static Border buttonBorder;

    static {
        textFiledBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colour.CDCDFE6),
                                            BorderFactory.createEmptyBorder(4, 8, 4, 8));

        searchFiledBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                                            BorderFactory.createEmptyBorder(4, 8, 4, 8));

        topPanelBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,1,0,Colour.CDCDFE6), null);

        viewBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colour.CDCDFE6), null);

        buttonBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Colour.CDCDFE6), null);
    }
}
