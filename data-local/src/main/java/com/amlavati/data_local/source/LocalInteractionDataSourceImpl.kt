package com.amlavati.data_local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.amlavati.data.data_source.local.LocalInteractionDataSource
import com.amlavati.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal val KEY_TOTAL_TAPS = intPreferencesKey("key_total_taps")

class LocalInteractionDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalInteractionDataSource {
    override fun getInteraction(): Flow<Interaction> =

        dataStore.data.map {

            Interaction(it[KEY_TOTAL_TAPS] ?: 0)
        }

    override suspend fun saveInteraction(interaction: Interaction) {
        dataStore.edit {
            it[KEY_TOTAL_TAPS] = interaction.totalClicks
        }
    }
}