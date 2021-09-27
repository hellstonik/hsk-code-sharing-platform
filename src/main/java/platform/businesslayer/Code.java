package platform.businesslayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "code")
public class Code {
    private static final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";

    @Id
    private String id;
    @Column(length = 2000)
    private String code;
    private LocalDateTime localDateTime;
    private int time;
    private int views;
    private int viewsLeft;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }

    public LocalDateTime getLocalDateTimeUnformatted() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViewsLeft() {
        return viewsLeft;
    }

    public void setViewsLeft(int viewsLeft) {
        this.viewsLeft = viewsLeft;
    }

    public long getTimeLeft() {
        long timeLeft = 0;
        if (getTime() > 0) {
            Duration secondPassedTillCreation = Duration.between(getLocalDateTimeUnformatted(),
                    LocalDateTime.now());
            timeLeft = getTime() - secondPassedTillCreation.getSeconds();
        }
        return timeLeft;

    }

    @Override
    public String toString() {
        return "Code{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", localDateTime=" + localDateTime +
                ", time=" + time +
                ", views=" + views +
                ", viewsLeft=" + viewsLeft +
                '}';
    }
}

