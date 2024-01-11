package team2.bookbridge.domain.enums;

import lombok.Getter;

@Getter
public enum WritingTool {
    CAN_ERASE_TOOL("연필, 샤프"),
    CANNOT_ERASE_TOOL("볼펜, 형광펜, 색연필");

    private final String writingTool;

    WritingTool(String writingTool) {
        this.writingTool = writingTool;
    }
}
