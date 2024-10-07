package com.example.newsapp.domain.mappers

import com.example.newsapp.data.api.model.SourceDM
import com.example.newsapp.domain.model.Source
import javax.inject.Inject

class SourceMapper @Inject constructor() {
    fun mapSourceDMToSource(sourceDM: SourceDM): Source{
        return Source(id = sourceDM.id, name = sourceDM.name ?: "")
    }
    fun mapSourcesDMToSources(sources: List<SourceDM>): List<Source>{
        return sources.map {
            mapSourceDMToSource(it)

        }
    }
}