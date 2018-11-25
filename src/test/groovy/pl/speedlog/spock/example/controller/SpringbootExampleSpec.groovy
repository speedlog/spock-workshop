package pl.speedlog.spock.example.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import pl.speedlog.spock.example.controller.dto.CarInfo
import pl.speedlog.spock.example.domain.service.CarService
import spock.lang.Specification

import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON

/**
 * Przykład testu z wstrzyknięciem stub'a do kontekstu springboot'a.
 *
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
@WebMvcTest(controllers = [CarController])
class SpringbootExampleSpec extends Specification {

    @Autowired
    MockMvc mvc

    @SpringBean
    // trzeba podać konkretny typ - nie może używać "def"
    CarService carService = Stub()

    @Autowired
    ObjectMapper objectMapper

    def "Should find car by vin"() {
        given:
            carService.getCarByVin("vin") >> new CarInfo("vin", "volkswagen", "passat")
        when:
            def response = mvc.perform(MockMvcRequestBuilders.get('/car?vin=vin')).andReturn().response
        then:
            response.status == HttpStatus.OK.value()
        and:
            with (objectMapper.readValue(response.contentAsString, Map)) {
                it.vin == 'vin'
                it.brand == 'volkswagen'
                it.model == 'passat'
            }
    }

    def "Should valid new car info and save only valid data"() {
        given:
            Map request = [
                    vin   : vin,
                    brand : brand,
                    model :  model
            ]
        when:
            def response = mvc.perform(MockMvcRequestBuilders.post('/car/add').contentType(APPLICATION_JSON).content(toJson(request))).andReturn().response
        then:
            response.status == HttpStatus.UNPROCESSABLE_ENTITY.value()
        and:
            with (objectMapper.readValue(response.contentAsString, Map)) {
                it.errors[0].code == 'MethodArgumentNotValidException'
                it.errors[0].path == path
                it.errors[0].userMessage == message
            }
        where:
            vin                 | brand     | model     | path      || message
            "5Y4AH30Y3DA019668" | "Honda"   | ""        | "model"   || "Model must be at least 1 character and at most 50 characters long."
            "5Y4AH30Y3DA019668" | ""        | "Civic"   | "brand"   || "Brand must be at least 1 character and at most 50 characters long."
            "5Y4AH30Y3DA01966"  | "Honda"   | "Civic"   | "vin"     || "VIN must be 17 characters long."
    }
}
