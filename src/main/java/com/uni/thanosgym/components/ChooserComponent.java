package com.uni.thanosgym.components;

import java.awt.Color;
import java.io.File;

import javax.swing.JPanel;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;

public class ChooserComponent extends JPanel {

    private File selected;

    public ChooserComponent(int width, int x, int y) {
        setLayout(null);
        setBackground(Color.WHITE);
        setSize(width, 30);
        setLocation(x, y);

        Typography label = new Typography("Sin seleccionar", Typography.Type.SMALL, width / 2, 0,
            width / 2);

        ButtonComponent button = new ButtonComponent("Seleccionar", 0, 0, width / 2 - 10,
                ButtonComponent.Type.SMALL);
        button.onClick(() -> {
            Response<File> response = FrameUtils.chooseImage(this);
            if (response.isSuccess()) {
                selected = response.getData();
                label.setText(selected.getName());
            } else {
                Messages.show("Selecciona una imagen");
            }
        });
        add(button);
        add(label);
    }
}
