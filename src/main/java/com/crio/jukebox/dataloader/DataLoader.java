package com.crio.jukebox.dataloader;

import java.util.List;

public interface DataLoader<T> {
    List<T> load(String filename);
}
