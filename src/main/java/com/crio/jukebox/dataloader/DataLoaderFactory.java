package com.crio.jukebox.dataloader;

import com.crio.jukebox.dataloader.enums.FileType;

public interface DataLoaderFactory<T> {
    DataLoader<T> getDataLoader(FileType type);
}
