"use client";
import { PageProps } from "../../../../.next/types/app/layout";
import SelectedFolderBase from "./components/SelectedFolderBase";
import { SelectedFolderSearchProvider } from "./contexts/SelectedFolderSearchProvider";



export default function SelectedFolder(props: PageProps) {
 

  return (
    <SelectedFolderSearchProvider>
      <SelectedFolderBase />
    </SelectedFolderSearchProvider>
  );
}
