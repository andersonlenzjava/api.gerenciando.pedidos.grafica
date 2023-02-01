package grafica.pedidos.api.domain.funcionario.funcionario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record FuncionarioRegister(

        @NotBlank
        String nome,

        @NotBlank
        @Length(min = 11, max = 11)
        String cpf,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        String dataNascimento,

        @NotBlank
        @Positive
        String telefone,

        @NotBlank
        String statusTrabalho

) {
}
