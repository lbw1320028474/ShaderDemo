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
void main()                                      
{
	vec2 uv = gl_FragCoord.xy / viewPort.xy;
	vec3 col = 0.5 + 0.6*cos(myTimes*2.0+uv.xyx+vec3(0,2,4));
	gl_FragColor = v_color * texture2D(u_texture, v_texCoords);
   	if(gl_FragCoord.x < lightWidth 
    	|| gl_FragCoord.x > viewPort.x - lightWidth
    	|| gl_FragCoord.y < lightWidth
    	|| gl_FragCoord.y > viewPort.y - lightWidth){
        gl_FragColor = vec4(col,1.0);
    }
    if(gl_FragCoord.x > (viewPort.x - (rangleRadius+lightWidth))
    && gl_FragCoord.y > (viewPort.y - (rangleRadius+lightWidth))){
        trueX =  gl_FragCoord.x;
        trueY = gl_FragCoord.y;
        curX = viewPort.x - (rangleRadius+lightWidth);
        curY = viewPort.y - (rangleRadius+lightWidth);
        if(sqrt(pow((trueX - curX),2.0)+pow((trueY - curY),2.0)) > rangleRadius){
            gl_FragColor = vec4(col,1.0);
        }
    }
    if(gl_FragCoord.x < (rangleRadius+lightWidth)
    && gl_FragCoord.y < (rangleRadius+lightWidth)){
        trueX =  gl_FragCoord.x;
        trueY = gl_FragCoord.y;
        curX = rangleRadius+lightWidth;
        curY = rangleRadius+lightWidth;
        if(sqrt(pow((trueX - curX),2.0)+pow((trueY - curY),2.0)) > rangleRadius){
            gl_FragColor = vec4(col,1.0);
        }
    }
    if(gl_FragCoord.x > viewPort.x - (rangleRadius+lightWidth)
    && gl_FragCoord.y < (rangleRadius+lightWidth)){
        trueX =  gl_FragCoord.x;
        trueY = gl_FragCoord.y;
        curX = viewPort.x - (rangleRadius+lightWidth);
        curY = rangleRadius+lightWidth;
        if(sqrt(pow((trueX - curX),2.0)+pow((trueY - curY),2.0)) > rangleRadius){
            gl_FragColor = vec4(col,1.0);
        }
    }
    if(gl_FragCoord.y > viewPort.y - (rangleRadius+lightWidth)
    && gl_FragCoord.x < (rangleRadius+lightWidth)){
        trueX =  gl_FragCoord.x;
        trueY = gl_FragCoord.y;
        curY = viewPort.y - (rangleRadius+lightWidth);
        curX = rangleRadius+lightWidth;
        if(sqrt(pow((trueX - curX),2.0)+pow((trueY - curY),2.0)) > rangleRadius){
            gl_FragColor = vec4(col,1.0);
        }
    }
} 