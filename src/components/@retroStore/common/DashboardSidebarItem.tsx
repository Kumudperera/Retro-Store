"use client";
import { Badge } from "@/components/ui/badge";
import Link from "next/link";
import { DashboardSidebarItemProps } from "./@types";
import { usePathname } from "next/navigation";

export function DashboardSidebarItem({
  item,
  url,
  icon,
  badge = undefined,
}: DashboardSidebarItemProps) {
  const pathname = usePathname();
  return (
    <Link
      href={url}
      className={`mx-[-0.65rem] md:mx-0 flex items-center gap-4 md:gap-3 rounded-lg px-3 py-2 hover:text-primary ${pathname === url ? "text-primary bg-muted" : "text-muted-foreground"}`}
      //className="flex items-center gap-3 rounded-lg bg-muted px-3 py-2 text-primary transition-all hover:text-primary"
    >
      {icon}
      {item}
      {badge !== undefined && (
        <Badge className="ml-auto flex h-6 w-6 shrink-0 items-center justify-center rounded-full">
          {badge}
        </Badge>
      )}
    </Link>
  );
}
