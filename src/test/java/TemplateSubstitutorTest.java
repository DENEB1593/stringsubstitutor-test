import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class TemplateSubstitutorTest {

    @Test
    void substitutionTest() {
        String givenTemplate = "안녕하세요, #{이름}님. #{서비스명}을 이용해주셔서 감사합니다.";

        Map<String, String> values = Map.of(
                "이름", "홍길동",
                "서비스명", "카페"
        );


        String result = TemplateSubstitutor.replace(givenTemplate, values);
        assertThat(result).doesNotContain("#{이름}");
        assertThat(result).doesNotContain("#{서비스명}");
        assertThat(result).contains("홍길동");
        assertThat(result).contains("카페");
    }


    @Test
    void substitutionExceptionTest() {
        String givenTemplate = "안녕하세요, #{닉네임}님. #{서비스명}을 이용해주셔서 감사합니다.";

        Map<String, String> values = Map.of(
                "이름", "홍길동",
                "서비스명", "카페"
        );

        assertThatThrownBy(
                () -> TemplateSubstitutor.replace(givenTemplate, values))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source does not contain '이름'");
    }

}