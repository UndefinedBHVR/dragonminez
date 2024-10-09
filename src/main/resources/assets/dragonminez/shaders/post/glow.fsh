#version 330 core

in vec2 TexCoords;

out vec4 FragColor;

uniform sampler2D screenTexture;
uniform float glowIntensity;

void main()
{
    vec4 color = texture(screenTexture, TexCoords);

    float brightness = dot(color.rgb, vec3(0.299, 0.587, 0.114));
    if (brightness > 0.8)
    {
        color.rgb += color.rgb * glowIntensity;
    }

    FragColor = color;
}