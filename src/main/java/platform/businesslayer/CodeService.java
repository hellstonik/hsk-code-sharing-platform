package platform.businesslayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.persistance.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeService {

    private static final int LATEST_LIMIT_QUANTITY = 10;
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String saveAndGetId(Code toSave) {
        toSave.setLocalDateTime(LocalDateTime.now());
        toSave.setId(String.valueOf(UUID.randomUUID().toString()));
        toSave.setViewsLeft(toSave.getViews());
        codeRepository.save(toSave);
        return toSave.getId();
    }

    public Code findCodeById(String id) {
        return codeRepository.findCodeById(id);
    }

    public List<Code> getLastTenCodeSnippets() {
        int thresholdSecondsForRestriction = 0;
        int thresholdViewsForRestriction = 0;
        List<Code> lastTenSnippets = codeRepository
                .findByTimeLessThanEqualAndViewsLessThanEqualOrderByLocalDateTimeDesc
                        (thresholdSecondsForRestriction, thresholdViewsForRestriction)
                .stream().limit(LATEST_LIMIT_QUANTITY).collect(Collectors.toList());

        return lastTenSnippets;
    }

    public long count() {
        return codeRepository.count();
    }

    public void deleteById(String id) {
        codeRepository.deleteById(id);
    }

    public void updateCode(Code code) {
        codeRepository.save(code);
    }

    public boolean isExpired(Code code) {

        Duration secondPassedTillCreation = Duration.between(code.getLocalDateTimeUnformatted(),
                LocalDateTime.now());
        boolean timeExpired = code.getTime() > 0 && secondPassedTillCreation.getSeconds() > code.getTime();
        boolean viewsExpired = code.getViews() > 0 && code.getViewsLeft() <= 0;

        return timeExpired || viewsExpired;
    }
}
