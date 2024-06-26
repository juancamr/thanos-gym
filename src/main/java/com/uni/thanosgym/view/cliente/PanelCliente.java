package com.uni.thanosgym.view.cliente;

import java.awt.Component;

import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.view.BasePanelForMainWindow;

public class PanelCliente extends BasePanelForMainWindow {

	@Override
    protected Component[] build() {
        Typography title = new Typography.Builder()
                .text("ThanosGym")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(contentWidth)
                .build();


        return new Component[] {
            title
        };
	}

}
