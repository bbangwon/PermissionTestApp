using UnityEngine;
using UnityEngine.UI;

public class use : MonoBehaviour
{
    public Image Im;

    public AndroidPlugin AP;

    public void ButtonDown()
    {
        StartCoroutine(AP.ShowGallery(val: (_tex, _spr) => { Im.sprite = _spr; }));
    }
}
