package platform.presentation.view;

import platform.businesslayer.Code;

import java.util.List;

public interface View {
    String getView();

    void fillViewWithModel(Code model);

    void fillViewWithSeveralModels(List<Code> model);
}