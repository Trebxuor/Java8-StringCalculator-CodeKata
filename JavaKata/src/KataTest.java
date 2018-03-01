import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class KataTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_return_0_for_an_empty_string() throws Exception {
        assertEquals(0, Kata.add(""));
    }

    @Test
    public void should_return_the_number_for_one_number() throws Exception {
        assertEquals(1, Kata.add("1"));
    }

    @Test
    public void should_return_the_sum_of_two_numbers() throws Exception {
        assertEquals(3, Kata.add("1,2"));
    }

    @Test
    public void should_return_sum_for_multiple_numbers() throws Exception {
        assertEquals(6, Kata.add("1,2,3"));
    }

    @Test
    public void should_return_the_sum_for_a_newline_delimiter() throws Exception {
        assertEquals(6, Kata.add("1,2\n3"));
    }

    @Test
    public void should_return_sum_for_custom_delimiters() throws Exception {
        assertEquals(6, Kata.add("//;\n1;2;3"));
    }

    @Test
    public void should_throw_an_exception_for_a_negative_numbers() throws Exception {
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Negatives: -2,-5");
        Kata.add("//;\n1;-2;3;-5");
    }

    @Test
    public void should_ignore_numbers_larger_than_1000() throws Exception {
        assertEquals(6, Kata.add("//;\n1;2000;2;3;1001"));
    }

    @Test
    public void should_allow_mutli_length_custom_delimiters() throws Exception {
        assertEquals(6, Kata.add("//[***]\n1***2***3"));
    }

    @Test
    public void should_allow_multi_custom_delimiters() throws Exception {
        assertEquals(6, Kata.add("//[*][$]\n1*2$3"));
    }

    @Test
    public void should_return_sum_for_multiple_mutli_length_custom_delimiters() throws Exception {
        assertEquals(6, Kata.add("//[***][abc]\n1***2abc3"));
    }

}