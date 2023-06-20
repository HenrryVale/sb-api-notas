package appnotas.dto;

/**
 * Clase genérica que representa una respuesta (DTO) con un mensaje y datos asociados.
 * Utiliza el patrón de diseño Builder para construir instancias de ResponseDTO.
 *
 * @param <T> el tipo de los datos asociados a la respuesta
 */
public class ResponseDTO<T> {
    private String message;
    private T data;

    /**
     * Constructor privado que recibe un objeto Builder para construir una instancia de ResponseDTO.
     *
     * @param builder el objeto Builder utilizado para construir la instancia
     */
    private ResponseDTO(Builder<T> builder) {
        this.message = builder.message;
        this.data = builder.data;
    }

    /**
     * Método getter para obtener el mensaje de la respuesta.
     *
     * @return el mensaje de la respuesta
     */
    public String getMessage() {
        return message;
    }

    /**
     * Método getter para obtener los datos de la respuesta.
     *
     * @return los datos de la respuesta
     */
    public T getData() {
        return data;
    }

    /**
     * Clase Builder para construir instancias de ResponseDTO.
     *
     * @param <T> el tipo de los datos asociados a la respuesta
     */
    public static class Builder<T> {
        private String message;
        private T data;

        /**
         * Método para establecer el mensaje de la respuesta.
         *
         * @param message el mensaje de la respuesta
         * @return el objeto Builder actualizado
         */
        public Builder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Método para establecer los datos de la respuesta.
         *
         * @param data los datos de la respuesta
         * @return el objeto Builder actualizado
         */
        public Builder<T> setData(T data) {
            this.data = data;
            return this;
        }

        /**
         * Método para construir una instancia de ResponseDTO utilizando el objeto Builder.
         *
         * @return una nueva instancia de ResponseDTO
         */
        public ResponseDTO<T> build() {
            return new ResponseDTO<>(this);
        }
    }
}
