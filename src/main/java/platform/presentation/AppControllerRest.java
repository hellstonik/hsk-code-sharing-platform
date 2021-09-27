package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.businesslayer.CodeService;
import platform.businesslayer.Code;
import platform.presentation.forms.FormForNewCode;
import platform.presentation.view.JsonView;
import platform.presentation.view.View;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class AppControllerRest {
    @Autowired
    private CodeService codeService;
    private View view;

    @GetMapping(path = {"/code/new"}, produces = MediaType.TEXT_HTML_VALUE)
    public String showFormToAddNewCode() {
        return new FormForNewCode().getForm();
    }

    @PostMapping(path = {"/api/code/new"})
    public String addNewCodeSnippet(@RequestBody Code code) {
        String id = codeService.saveAndGetId(code);
        return "{ \"id\" : \"" + id + "\" }";
    }

    @GetMapping(path = {"/api/code/{id}"})
    public String getSnippetById(@PathVariable String id) {
        Code code = codeService.findCodeById(id);

        if (code == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        if (codeService.isExpired(code)) {
            codeService.deleteById(id);
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        if (code.getViews() > 0) {
            code.setViewsLeft(code.getViewsLeft() - 1);
            codeService.updateCode(code);
        }

        view = new JsonView();
        view.fillViewWithModel(code);
        return view.getView();
    }

    @GetMapping(path = {"/api/code/latest"})
    public String getLatestSnippets() {
        view = new JsonView();
        List<Code> codeSnippets = codeService.getLastTenCodeSnippets();
        view.fillViewWithSeveralModels(codeSnippets);
        return view.getView();
    }

}

