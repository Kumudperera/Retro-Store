"use client";
import React, { createContext, ReactNode, useContext, useMemo } from "react";

import { useReducerWithThunk } from "@/hooks/use-reducer-thunk";
import SelectedFolderReducer from "./SelectedFolderReducer";
import { SelectedFoldersData } from "../@types";

const initialData: SelectedFoldersData = {
  page_info: {
    page: 1,
    rows: 5,
  },
  paged_selected_folders: undefined
};

const SelectedFolderSearchContext = createContext<{
  state: SelectedFoldersData;
  dispatch: React.Dispatch<any>;
}>({
  state: initialData,
  dispatch: () => null,
});

export const SelectedFolderSearchProvider = ({
  children,
}: {
  children: ReactNode;
}) => {
  const [state, dispatch] = useReducerWithThunk(
    SelectedFolderReducer,
    initialData,
  );

  const contextValue = useMemo(
    () => ({ state: { ...state }, dispatch }),
    [state],
  );

  return (
    <SelectedFolderSearchContext.Provider value={contextValue}>
      {children}
    </SelectedFolderSearchContext.Provider>
  );
};

export const useSelectedFolderSearch = () =>
  useContext(SelectedFolderSearchContext);
