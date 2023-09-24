package org.akanework.gramophone.ui.adapters

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.media3.common.MediaItem
import org.akanework.gramophone.R
import org.akanework.gramophone.logic.utils.SupportComparator

class SongDecorAdapter(
    context: Context,
    songCount: Int,
    songAdapter: SongAdapter,
    canSort: Boolean,
) : BaseDecorAdapter<SongAdapter, MediaItem>
    (context, songCount, songAdapter, R.plurals.songs, canSort) {
    private var sortStatus = 0

    override fun onSortButtonPressed(popupMenu: PopupMenu) {
        popupMenu.inflate(R.menu.sort_menu_songs)

        when (sortStatus) {
            0 -> {
                popupMenu.menu.findItem(R.id.name).isChecked = true
            }

            1 -> {
                popupMenu.menu.findItem(R.id.artist).isChecked = true
            }

            2 -> {
                popupMenu.menu.findItem(R.id.album).isChecked = true
            }
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.name -> {
                    if (!menuItem.isChecked) {
                        adapter.sort(
                            SupportComparator
                                .createAlphanumericComparator { it2 -> it2.mediaMetadata.title!! })
                        menuItem.isChecked = true
                        sortStatus = 0
                    }
                }

                R.id.artist -> {
                    if (!menuItem.isChecked) {
                        adapter.sort(
                            SupportComparator
                                .createAlphanumericComparator { it2 -> it2.mediaMetadata.artist!! })
                        menuItem.isChecked = true
                        sortStatus = 1
                    }
                }

                R.id.album -> {
                    if (!menuItem.isChecked) {
                        adapter.sort(
                            SupportComparator
                                .createAlphanumericComparator { it2 -> it2.mediaMetadata.albumTitle!! })
                        menuItem.isChecked = true
                        sortStatus = 2
                    }
                }
            }
            true
        }
    }
}
