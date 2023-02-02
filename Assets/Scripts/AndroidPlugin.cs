using System.Collections;
using UnityEngine;
using UnityEngine.Events;

public class AndroidPlugin : MonoBehaviour
{
    private AndroidJavaObject Kotlin;
    private (Texture2D _tex, Sprite _spr) CallBack = (null, null);

    private void Awake()
    {
        Kotlin = new AndroidJavaObject("com.breadone.PermissionTestApp.UnityGalleryActivity");
    }

    public IEnumerator ShowGallery(UnityAction<Texture2D, Sprite> val)
    {
        Kotlin.Call("Open");
        yield return new WaitUntil(() => CallBack._tex != null && CallBack._spr != null);
        val(CallBack._tex, CallBack._spr);
        CallBack = (null, null);
    }

    private void getImage(string path)
    {
        var www = new WWW($"file://{path}");
        var _tex  = www.texture;
        var _spr = Sprite.Create(_tex, new Rect(0f, 0f, _tex.width, _tex.height), new Vector2(0.5f, 0.5f),
            pixelsPerUnit: 100);

        CallBack = (_tex, _spr);
    }
}
