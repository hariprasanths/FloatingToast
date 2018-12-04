# FloatingToast-Android

An android library to make customisable floating animated toasts

![Screenshots](https://media.giphy.com/media/ZxLbLvUt49O0WtLEuf/giphy.gif)

## Getting Started

#### In your build.gradle

```groovy
dependencies {
    implementation 'hari.floatingtoast:floatingtoast:0.1.0'
}
```

## Usage

#### Create Floating Toasts

First, instantiate a FloatingToast object with one of the makeToast() methods.
 This method takes three parameters: the view which calls the toast (recommended) or the activity context,
 the text message, and the duration for the toast.
 It returns a properly initialized FloatingToast object.
 You can display the toast notification with show(),
 as shown in the following example:

```java
    Button button = findViewById(R.id.button);
    String text = "Hello toast!";
    int duration = FloatingToast.LENGTH_MEDIUM;

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FloatingToast toast = FloatingToast.makeToast(button, text, FloatingToast.LENGTH_MEDIUM);
            toast.show();
        }
    });
```

You can also chain your methods and avoid holding on to the Toast object, like this:

```java
    FloatingToast.makeToast(button, text, FloatingToast.LENGTH_MEDIUM).show();
```

You can use the activity context to instantiate the FloatingToast object, like this:

```java
    FloatingToast.makeToast(MainActivity.this, text, FloatingToast.LENGTH_MEDIUM).show();
```

```
    NOTE: Always use the view (which was used to call the toast) to
    create the FloatingToast object wherever possible rather than
    using the activity context.
```

An example with all the customisations:

```java
    Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/custom_font.ttf");

    //Duration of 1250 millis
    //Gravity - Mid Top                 (Default is Center)
    //Fade out duration of 1000 millis  (Default is 750 millis)
    //Text size - 12dp                  (Default is 16dp)
    //Background blur - On              (Default is On)
    //Float distance - 30px             (Default is 40px)
    //Text color - White
    //Text shadow - On                  (Default is Off)
    //Custom font                       (No custom font is provided by default)
    FloatingToast.makeToast(button, text, FloatingToast.LENGTH_LONG)
            .setGravity(FloatingToast.GRAVITY_MID_TOP)
            .setFadeOutDuration(FloatingToast.FADE_DURATION_LONG)
            .setTextSizeInDp(12)
            .setBackgroundBlur(true)
            .setFloatDistance(FloatingToast.DISTANCE_SHORT)
            .setTextColor(Color.parseColor("#ffffff"))
            .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
            .setTextTypeface(customFont)
            .show();    //Show toast at the specified fixed position
```

You can also set the toast's position as dynamic(i.e. show the toast at the touch
position) like this:

```java
    FloatingToast.makeToast(button, text, FloatingToast.LENGTH_MEDIUM)
            .showAtTouchPosition();
```

## Summary

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h4>Constants</h4></th></tr>
    <tr>
        <td colspan="2" left><b>Duration</b> -
            Duration of the toast being shown. This time could be user-definable.
        </td>
    </tr>
    <tr>
        <td valign="top" width="30%"><code>int</code></td>
        <td valign="top" width="100%">
            <a ><span>LENGTH_LONG</span></a>
            <p>Show the toast for a long period of time. (1250 millis)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>LENGTH_MEDIUM</span></a>
            <p>Show the toast for a certain period of time. (1000 millis)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>LENGTH_SHORT</span></a>
            <p>Show the toast for a short period of time. (750 millis)</p>
        </td>
    </tr>
    <!-- <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>LENGTH_TOO_LONG</span></a>
            <p>Show the toast for a certain period of time. (1500 millis)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>LENGTH_QUICK</span></a>
            <p>Show the toast for a certain period of time. (500 millis)</p>
        </td>
    </tr> -->
    <tr>
        <td valign="top" colspan="2" left><b>Fade out duration</b> -
            Fade out duration of the toast. This time could be user-definable.<br/>
            See also <a href="#setFadeOutDuration">setFadeOutDuration(int)</a>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>FADE_DURATION_LONG</span></a>
            <p>Fade the toast within a long period of time. (1000 millis)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>FADE_DURATION_MEDIUM</span></a>
            <p>Fade the toast within a certain period of time. (750 millis)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>FADE_DURATION_SHORT</span></a>
            <p>Fade the toast within a short period of time. (500 millis)</p>
        </td>
    </tr>
    <tr>
        <td valign="top" colspan="2" left><b>Float distance</b> -
            Float distance of the toast. This distance could be user-definable.<br/>
            See also <a href="#setFloatDistance">setFloatDistance(float)</a>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>float</td>
        <td valign="top" width="100%">
            <a ><span>DISTANCE_LONG</span></a>
            <p>Float the toast for a long distance. (50 px)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>float</td>
        <td valign="top" width="100%">
            <a ><span>DISTANCE_MEDIUM</span></a>
            <p>Float the toast for a certain distance. (40 px)</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code><code>float</code></td>
        <td valign="top" width="100%">
            <a ><span>DISTANCE_SHORT</span></a>
            <p>Float the toast for a short distance. (30 px)</p>
        </td>
    </tr>
    <tr>
        <td valign="top" colspan="2" left><b>Gravity</b> -
            Location at which the toast should appear on the screen. This could be user-definable.<br/>
            See also <a href="#setGravity">setGravity(int)</a>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>GRAVITY_MID_TOP</span></a>
            <p>Show the toast at 25% from the top of the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>GRAVITY_CENTER</span></a>
            <p>Show the toast at the center of the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>GRAVITY_MID_BOTTOM</span></a>
            <p>Show the toast at 75% from the top of the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>GRAVITY_TOP</span></a>
            <p>Show the toast at the top of the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>GRAVITY_BOTTOM</span></a>
            <p>Show the toast at the bottom of the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top" colspan="2" left><b>Style</b> -
           Style of the text in the toast.  This style could be user-definable.<br/>
            See also <a href="#setTextStyle">setTextStyle(int)</a>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>STYLE_BOLD</span></a>
            <p>Bold style for the text in the toast.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>STYLE_ITALIC</span></a>
            <p>Italic style for the text in the toast.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>STYLE_NORMAL</span></a>
            <p>Normal style for the text in the toast.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>int</td>
        <td valign="top" width="100%">
            <a ><span>STYLE_BOLD_ITALIC</span></a>
            <p>Bold Italic style for the text in the toast.</p>
        </td>
    </tr>
</tbody>
</table>

---

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h4>Public methods</h4></th></tr>
    <tr>
        <td valign="top" width="30%"><code>static FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#maketoast">makeToast</a>(View view, String text, int duration)</code>
            <p>Make a standard toast that just contains a text view.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>static FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#maketoast-1">makeToast</a>(View view, int resId, int duration)</code>
            <p>Make a standard toast that just contains a text view with the text from a resource.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>static FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#maketoast-2">makeToast</a>(Activity activity, String text, int duration)</code>
            <p>Make a standard toast that just contains a text view.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>static FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#maketoast-3">makeToast</a>(Activity activity, int resId, int duration)</code>
            <p>Make a standard toast that just contains a text view with the text from a resource.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setgravity">setGravity</a>(int gravity)</code>
            <p>Set the location at which the notification should appear on the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setgravity-1">setGravity</a>(int gravity, int xOffset, int yOffset)</code>
            <p>Set the location at which the notification should appear on the screen.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setFadeOutDuration">setFadeOutDuration</a>(int fadeOutDuration)</code>
            <p>Set the fade out duration of the toast.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setTextColor">setTextColor</a>(int color)</code>
            <p>Sets the text color for all the states (normal, selected,
            focused) to be this color.
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setTextTypeface">setTextTypeface</a>(Typeface typeface)</code>
            <p>Sets the typeface and style in which the text should be displayed.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setTextStyle">setTextStyle</a>(int style)</code>
            <p>Sets the style in which the text should be displayed,
                and turns on the fake bold and italic bits in the Paint if the
                Typeface that you provided does not have all the bits in the
                style that you specified.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setTextSizeInSp">setTextSizeInSp</a>(float sizeInSp)</code>
            <p>Set the default text size to the given value, interpreted as "scaled
                pixel" units.  This size is adjusted based on the current density and
                user font size preference.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setTextSizeInDp">setTextSizeInDp</a>(float sizeInDp)</code>
            <p>Set the default text size to the given value, interpreted as "density
                independent pixel" units. This size is adjusted based on the current density.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setTextSizeCustomUnit">setTextSizeCustomUnit</a>(int unit, float size)</code>
            <p>Set the default text size to a given unit and value.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setFloatDistance">setFloatDistance</a>(float floatDistance)</code>
            <p>Sets the distance of the toast till which it floats.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>FloatingToast</td>
        <td valign="top" width="100%">
            <code><a href="#setShadowLayer">setShadowLayer</a>(float shadowRadius, float shadowDx, float shadowDy, int shadowColor)</code>
            <p>Gives the text a shadow of the specified blur radius and color, the specified
                distance from its drawn position.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>void</td>
        <td valign="top" width="100%">
            <code><a href="#setBackgroundBlur">setBackgroundBlur</a>(boolean bool)</code>
            <p>Sets the blur background for the text in the toast.</p>
        </td>
    </tr>
    <tr>
        <td valign="top"><code>void</td>
        <td valign="top" width="100%">
            <code><a href="#show">show</a>()</code>
            <p>Show the view for the specified duration.</p>
        </td>
    </tr>

</tbody>
</table>

## Public methods

### makeToast

```java
    makeToast (View view, String text, int duration)
```

Make a standard toast that just contains a text view.<br/>
(Recommended method over makeToast(Activity, String, int)

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">view</td>
        <td width="100%">
            <code>View:</code> The view which was used to call the toast.
        </td>
    </tr>
    <tr>
        <td width="30%">text</td>
        <td width="100%">
            <code>String:</code> The text to show. Can be formatted text.
        </td>
    </tr>
    <tr>
        <td width="30%">duration</td>
        <td width="100%">
            <code>int:</code> Duration of the toast to be shown in milliseconds.
            Either user-defined int or predefined constant duration.
        </td>
    </tr>
</tbody>
</table>

<br/>

### makeToast

```java
    makeToast (View view, int resId, int duration)
```

Make a standard toast that just contains a text view with the text from a resource.<br/>
@throws Resources.NotFoundException if the resource can't be found.<br/>
 (Recommended method over makeToast(Activity, int, int)

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">view</td>
        <td width="100%">
            <code>View:</code> The view which was used to call the toast.
        </td>
    </tr>
    <tr>
        <td width="30%">resId</td>
        <td width="100%">
            <code>int:</code> The resource id of the string resource to use.  Can be formatted text.
        </td>
    </tr>
    <tr>
        <td width="30%">duration</td>
        <td width="100%">
            <code>int:</code> Duration of the toast to be shown in milliseconds.
            Either user-defined int or predefined constant duration.
        </td>
    </tr>
</tbody>
</table>

<br/>

### makeToast

```java
    makeToast (Activity activity, String text, int duration)
```

Make a standard toast that just contains a text view.<br/>
(Only use this method if not calling from a view)

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">actvity</td>
        <td width="100%">
            <code>Activity:</code> The activity context. Usually your android.app.Activity object.
        </td>
    </tr>
    <tr>
        <td width="30%">text</td>
        <td width="100%">
            <code>String:</code> The text to show. Can be formatted text.
        </td>
    </tr>
    <tr>
        <td width="30%">duration</td>
        <td width="100%">
            <code>int:</code> Duration of the toast to be shown in milliseconds.
            Either user-defined int or predefined constant duration.
        </td>
    </tr>
</tbody>
</table>

<br/>

### makeToast

```java
    makeToast (Activity activity, int resId, int duration)
```

Make a standard toast that just contains a text view with the text from a resource.<br/>
@throws Resources.NotFoundException if the resource can't be found.<br/>
(Only use this method if not calling from a view)

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">actvity</td>
        <td width="100%">
            <code>Activity:</code> The activity context. Usually your android.app.Activity object.
        </td>
    </tr>
    <tr>
        <td width="30%">resId</td>
        <td width="100%">
            <code>int:</code> The resource id of the string resource to use.  Can be formatted text.
        </td>
    </tr>
    <tr>
        <td width="30%">duration</td>
        <td width="100%">
            <code>int:</code> Duration of the toast to be shown in milliseconds.
            Either user-defined int or predefined constant duration.
        </td>
    </tr>
</tbody>
</table>

<br/>

### setGravity

```java
    setGravity (int gravity)
```

Set the location at which the notification should appear on the screen.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h6>Parameters</h6></th></tr>
    <tr>
        <td width="30%">gravity</td>
        <td width="100%">
            <code>int:</code> Position of toast in the screen.
        </td>
    </tr>
</tbody>
</table>

<br/>

### setGravity

```java
    setGravity (int gravity, int xOffset, int yOffset)
```

Set the location at which the notification should appear on the screen.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h6>Parameters</h6></th></tr>
    <tr>
        <td width="30%">gravity</td>
        <td width="100%">
            <code>int:</code>  Position of toast in the screen.
        </td>
    </tr>
    <tr>
        <td width="30%">xOffset</td>
        <td width="100%">
            <code>int:</code>  X offset in pixels to apply to the gravity's location.
        </td>
    </tr>
    <tr>
        <td width="30%">yOffset</td>
        <td width="100%">
            <code>int:</code>  Y offset in pixels to apply to the gravity's location
        </td>
    </tr>
</tbody>
</table>

<br/>

### setFadeOutDuration

```java
    setFadeOutDuration (int fadeOutDuration)
```

Set the fade out duration of the toast. Total animation time of the toast - duration + fadeOutDuration

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">fadeOutDuration</td>
        <td width="100%">
            <code>int:</code> Fade out duration in milliseconds
        </td>
    </tr>
</tbody>
</table>

<br/>

### setTextColor

```java
    setTextColor (int color)
```

Sets the text color for all the states (normal, selected, focused) to be this color.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">color</td>
        <td width="100%">
            <code>int:</code> A color value in the form 0xAARRGGBB.<br/>
            Do not pass a resource ID. To get a color value from a resource ID,
             call android.support.v4.content.ContextCompat#getColor(Context, int) getColor
        </td>
    </tr>
</tbody>
</table>

<br/>

### setTextTypeface

```java
    setTextTypeface (Typeface typeface)
```
Sets the typeface and style in which the text should be displayed.
Note that not all Typeface families actually have bold and italic
variants, so you may need to use setTextStyle(int) to get the
appearance that you actually want.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">typeface</td>
        <td width="100%">
            <code>Typeface</code>
        </td>
    </tr>
</tbody>
</table>

<br/>

### setTextStyle

```java
    setTextStyle (int style)
```

Sets the style in which the text should be displayed,
and turns on the fake bold and italic bits in the Paint if the
Typeface that you provided does not have all the bits in the
style that you specified.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">style</td>
        <td width="100%">
            <code>int</code>
        </td>
    </tr>
</tbody>
</table>

<br/>

### setTextSizeInSp

```java
    setTextSizeInSp (float sizeInSp)
```

Set the default text size to the given value, interpreted as "scaled
 pixel" units.  This size is adjusted based on the current density and
 user font size preference.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">sizeInSp</td>
        <td width="100%">
            <code>float:</code> The scaled pixel size.
        </td>
    </tr>
</tbody>
</table>

<br/>

### setTextSizeInDp

```java
    setTextSizeInDp (int sizeInDp)
```

Set the default text size to the given value, interpreted as
"independent pixel" units.  This size is adjusted based on the current density.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">sizeInDp</td>
        <td width="100%">
            <code>int:</code> The density independent pixel size.
        </td>
    </tr>
</tbody>
</table>

<br/>

### setTextSizeCustomUnit

```java
    setTextSizeCustomUnit (int unit, float size)
```

Set the default text size to a given unit and value. See
android.util.TypedValue for the possible dimension units.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">unit</td>
        <td width="100%">
            <code>int:</code> The desired dimension unit.
        </td>
    </tr>
    <tr>
        <td width="30%">size</td>
        <td width="100%">
            <code>float:</code> The desired size in the given units.
        </td>
    </tr>
</tbody>
</table>

<br/>

### setFloatDistance

```java
    setFloatDistance (float floatDistance)
```

Sets the distance of the toast till which it floats.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">floatDistance</td>
        <td width="100%">
            <code>float:</code> Distance in pixels
        </td>
    </tr>
</tbody>
</table>

<br/>

### setShadowLayer

```java
    setShadowLayer (float shadowRadius, float shadowDx, float shadowDy, int shadowColor)
```

Gives the text a shadow of the specified blur radius and color, the specified
distance from its drawn position.
The text shadow produced does not interact with the properties on view
that are responsible for real time shadows,
```elevation``` and ```translationZ```.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">shadowRadius</td>
        <td width="100%">
            <code>float</code>
        </td>
    </tr>
    <tr>
        <td width="30%">shadowDx</td>
        <td width="100%">
            <code>float</code>
        </td>
    </tr>
    <tr>
        <td width="30%">shadowDy</td>
        <td width="100%">
            <code>float</code>
        </td>
    </tr>
    <tr>
        <td width="30%">shadowColor</td>
        <td width="100%">
            <code>int</code>
        </td>
    </tr>
</tbody>
</table>

<br/>

### setBackgroundBlur

```java
    setBackgroundBlur (boolean bool)
```

Sets the blur background for the text in the toast.

<table width="100%">
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">bool</td>
        <td width="100%">
            <code>boolean:</code> false disables the blur. Default is true.
        </td>
    </tr>
</tbody>
</table>

<br/>

### showAtTouchPosition

```java
    showAtTouchPosition (View view)
```

Show the view for the specified duration at the touch position.

<table width=100%>
<tbody>
    <tr><th colspan="2" left align="left"><h5>Parameters</h5></th></tr>
    <tr>
        <td width="30%">view</td>
        <td width="100%">
            <code>View:</code> View over which the toast is to be shown
        </td>
    </tr>
</tbody>
</table>

<br/>

### show

```java
    show()
```

Show the view for the specified duration.

<br/>

## License

Copyright 2018 hariprasanths

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.