#version 150

in vec4 vertexColor;
in vec2 texCoord0;
in vec2 lightmapUV;

uniform vec4 ColorModulator;
uniform sampler2D textureSampler;
uniform sampler2D lightmapSampler;

out vec4 fragColor;

void main() {
    vec4 texColor = texture(textureSampler, texCoord0);
    vec4 color = vertexColor * texColor;

    if (color.a == 0.0) {
        discard;
    }

    // Muestrear el lightmap para efectos de brillo
    vec4 lightmapColor = texture(lightmapSampler, lightmapUV);

    // Añadir componente de emisión para el brillo
    vec3 emission = color.rgb * 0.8; // Ajusta el factor según el brillo deseado

    fragColor = vec4(color.rgb + emission, color.a) * ColorModulator * lightmapColor;
}