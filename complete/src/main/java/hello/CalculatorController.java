package hello;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/")
    public String index(@RequestParam(name = "expression", required = false) String expression) {
        String resultText = "";

        if (expression != null && !expression.trim().isEmpty()) {
            try {
                Expression exp = new ExpressionBuilder(expression).build();
                double result = exp.evaluate();
                resultText = "<h3>Результат: " + result + "</h3>";
            } catch (Exception e) {
                resultText = "<h3 style='color:red;'>Помилка у виразі: " + e.getMessage() + "</h3>";
            }
        }

        return "<html>" +
               "<head><title>Web Calculator</title></head>" +
               "<body style='font-family: sans-serif; margin: 40px;'>" +
               "<h2>Веб-калькулятор (Maven + Spring Boot)</h2>" +
               "<form action='/' method='get'>" +
               "  <label for='expression'>Введіть математичний вираз:</label><br><br>" +
               "  <input type='text' id='expression' name='expression' value='" + (expression != null ? expression : "") + "' placeholder='2+2*2' style='width: 300px; padding: 5px;'><br><br>" +
               "  <button type='submit' style='padding: 5px 15px;'>Обчислити</button>" +
               "</form>" +
               "<br>" + resultText +
               "</body>" +
               "</html>";
    }
}