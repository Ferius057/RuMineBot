package kz.ferius_057.ruminebot.object;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 06.07.2022 | 0:19 ⭐
 */
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullName {
    String noPush, push;
}