#ifdef GL_ES
precision mediump float;    
#endif    
varying vec4 v_color;    
varying vec2 v_texCoords;    
uniform sampler2D u_texture;
uniform vec2 viewPort;
uniform float myTimes;
float lightWidth = 7.0;
float rangleRadius = 10.0;
float trueX = 0.0;
float trueY = 0.0;
float curX = 0.0;
float curY = 0.0;
float colorTYpe = 0.0;
float step_w = 0.0015625;
float step_h = 0.0027778;
float maxW   = 0.04;

void main(void)
{
    float x = v_texCoords.x - maxW*sin((v_texCoords.y*80.0 - myTimes*7.50));

    gl_FragColor = texture2D(u_texture, vec2(x, v_texCoords.y));
}