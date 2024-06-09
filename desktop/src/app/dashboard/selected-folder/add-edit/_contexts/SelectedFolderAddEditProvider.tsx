"use client";
import React, { createContext, ReactNode, useContext, useMemo } from "react";

import { useReducerWithThunk } from "@/hooks/use-reducer-thunk";
import SelectedFolderReducer from "./SelectedFolderAddEditReducer";
import { SelectedFolderAddEditData } from "../../@types";

const initialData: SelectedFolderAddEditData = {
  selected_folder: undefined,
  all_periods: []
};

const SelectedFolderAddEditContext = createContext<{
  state: SelectedFolderAddEditData;
  dispatch: React.Dispatch<any>;
}>({
  state: initialData,
  dispatch: () => null,
});

export const SelectedFolderAddEditProvider = ({
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
    <SelectedFolderAddEditContext.Provider value={contextValue}>
      {children}
    </SelectedFolderAddEditContext.Provider>
  );
};

export const useSelectedFolderAddEdit = () =>
  useContext(SelectedFolderAddEditContext);
