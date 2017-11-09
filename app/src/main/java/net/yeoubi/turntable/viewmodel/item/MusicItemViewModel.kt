package net.yeoubi.turntable.viewmodel.item

import net.yeoubi.turntable.common.constants.Extras
import net.yeoubi.turntable.data.Music
import net.yeoubi.turntable.view.MusicActivity
import net.yeoubi.turntable.viewmodel.common.ItemViewModel

/**
 * @author InJung Chung
 */

class MusicItemViewModel(item: Music) : ItemViewModel<Music>(item) {

    fun onClick() {
        activity?.startActivity(
            MusicActivity::class,
            Extras.ID, item.id
        )
    }
}
