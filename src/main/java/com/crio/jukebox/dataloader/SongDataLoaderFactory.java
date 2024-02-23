package com.crio.jukebox.dataloader;

import com.crio.jukebox.dataloader.enums.FileType;
import com.crio.jukebox.entities.Song;

public class SongDataLoaderFactory implements DataLoaderFactory<Song> {

    @Override
    public DataLoader<Song> getDataLoader(FileType type) {
        switch(type) {
            case CSV:
                return new SongCsvLoader();
            default:
                throw new IllegalArgumentException("File Type not supported.");
        }
    }
    
}
